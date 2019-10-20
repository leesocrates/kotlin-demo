package cn.example.myapplication.fragment

import android.util.Log
import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.R
import cn.example.myapplication.network.log
import kotlinx.android.synthetic.main.fragment_socket.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket

class SocketFragment: BaseFragment() {
    var socketServer: ServerSocket? = null
    var socketClient: Socket? = null
    var isClientClose: Boolean = false
    var isServerClose: Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_socket
    }

    override fun initView() {
        startSocketServer.setOnClickListener {
            startServer(5000)
        }
        stopSocketServer.setOnClickListener {
            stopServer()
        }
        startSocketClient.setOnClickListener {
            startClient(5000)
        }
        stopSocketClient.setOnClickListener {
            stopClient()
        }
    }

    private fun startServer(port: Int){
        socketServer = ServerSocket(port)
        isServerClose = false
        GlobalScope.launch(Dispatchers.Default) {
            while (!isServerClose){
                Log.i(TAG, "socketServer start accept()")
                val socket = socketServer?.accept()
                Log.i(TAG, "socketServer accept a client")
                supervisorScope {
                    Log.i(TAG, "handle clientSocket ")
                    val ins = socket?.getInputStream()
                    val ops = socket?.getOutputStream()
                    try{
                        while (true){
                            val buffer = ins?.read()
                            Log.i(TAG, "msg from client is $buffer")
                            if(buffer == 0){
                                break
                            }
                            buffer?.let {
                                if(socket?.isConnected){
                                    ops?.write(1)
                                    ops?.flush()
                                }
                            }
                        }
                    } catch (e: Exception){
                        e.printStackTrace()
                    } finally {
                        try{
                            socket?.shutdownOutput()
                            socket?.shutdownInput()
                        } catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                }
            }
            socketServer?.close()
        }
    }

    private fun stopServer(){
        isServerClose = true
    }

    private fun startClient(port: Int){
        GlobalScope.launch(Dispatchers.Default){
            socketClient = Socket("127.0.0.1",port)
            isClientClose = false
            Log.i(TAG, "socketClient finish $socketClient")
            val ins = socketClient?.getInputStream()
            val ops = socketClient?.getOutputStream()
            var i = 1
            ops?.write(i++)
            ops?.flush()
            try {
                while (!isClientClose){
                    val msg = ins?.read()
                    Log.i(TAG, "msg from server is $msg")
                    delay(3000)
                    ops?.write(i++)
                }
                ops?.write(0)
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                socketClient?.shutdownInput()
                socketClient?.shutdownOutput()
                socketClient?.close()
            }
        }
    }

    private fun stopClient(){
        isClientClose = true
    }
}