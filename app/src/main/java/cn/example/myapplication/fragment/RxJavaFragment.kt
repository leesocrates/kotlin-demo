package cn.example.myapplication.fragment

import android.util.Log
import cn.example.myapplication.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.HashMap
import java.util.concurrent.ConcurrentHashMap

class RxJavaFragment :AppBaseFragment(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_rxjava
    }

    override fun initView() {
        val observer = object: Observer<String> {
            override fun onSubscribe(d: Disposable?) {

            }

            override fun onComplete() {
                Log.i(TAG, "observer onComplete")
            }

            override fun onNext(value: String?) {
                Log.i(TAG, "observer onNext $value")
            }

            override fun onError(e: Throwable?) {
                Log.i(TAG, "observer onNext $e")
            }

        }
        val observable = object: Observable<String>(){
            override fun subscribeActual(observer: Observer<in String>?) {

            }

        }
        Observable.just("a")
                .map(object : Function<String, String>{
                    override fun apply(t: String?): String {
                        return "$t 1"
                    }

                })
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer)
    }

}