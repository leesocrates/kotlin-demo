package cn.example.myapplication

class MyClassLoader : ClassLoader() {
    override fun loadClass(name: String?): Class<*> {
        return super.loadClass(name)
    }

    override fun findClass(name: String?): Class<*> {
        return super.findClass(name)
    }
}