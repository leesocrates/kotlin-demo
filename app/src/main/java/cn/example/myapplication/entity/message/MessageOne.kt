package cn.example.myapplication.entity.message

data class MessageOne(var name: String, var age: Int){
    override fun toString(): String {
        return "MessageOne(name='$name', age=$age)"
    }
}