package com.example.todolist
import android.graphics.Paint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var todoList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var todoEdit: EditText

    //온 크리에이트니까 코드 시작 알리는거
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ArrayList 초기화
        todoList = ArrayList()

        //ArrayAdapter 초기화(context, layout, list)
        adapter = ArrayAdapter(this, R.layout.list_item, todoList)

        //UI객체 생성
        val listView: ListView = findViewById(R.id.list_view)
        val addBtn: Button = findViewById(R.id.add_btn)
        todoEdit = findViewById(R.id.todo_edit)

        //Adapter 적용
        listView.adapter = adapter

        //버튼 이벤트
        addBtn.setOnClickListener {
            //할 일 추가
            addItem()
        }

        //리스트 아이템 클릭 이벤트
        listView.setOnItemClickListener {adapterView, view, i, l ->

            val textView: TextView = view as TextView

            //취소선 넣기
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

    } //onCreate

    private fun addItem() {

        //입력값 변수에 담기
        val todo: String = todoEdit.text.toString()

        //값이 비워있지 않다면
        if (todo.isNotEmpty()) {

            //할일 추가
            todoList.add(todo)

            //적용
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "할 일을 적어주세요", Toast.LENGTH_SHORT).show()
        }
    }

}