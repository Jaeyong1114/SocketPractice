package com.example.socketpractice


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import com.example.socketpractice.databinding.ActivityMainBinding
import java.io.*

import java.net.Socket


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)








        /*Thread {
            try {
                val socket = Socket("10.0.2.2",8080)
                val printer = PrintWriter(socket.getOutputStream())
                val reader =  BufferedReader(InputStreamReader(socket.getInputStream()))


                //서버에서는 읽는것을 먼저하므로 클라이언트에서는 쓰는것을 먼저해줘야함

                printer.println("GET / HTTP/1.1") //HTTP1.1 로  요청하겠다
                printer.println("Host: 192.168.56.1:8080") //호스트는 누구에게 요청을 하는지
                printer.println("User-Agent: android") // 현재 서버는 데이터를 받지않으므로 위에줄포함 안적어도 동작은 함
                printer.println("\r\n")
                printer.flush()

                // 서버쪽에서 읽는게 끝났으므로 이번엔 클라이언트가 읽을 차례

                var input: String? = "-1"
                while(input !=null){
                    input = reader.readLine()
                    Log.e("Client",input)
                }
                reader.close()
                printer.close()
                socket.close()

            } catch (e: Exception){
                Log.e("Client",e.toString())

            }


        }.start()*/


    }
}