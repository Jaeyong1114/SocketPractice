package com.example.socketpractice


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.socketpractice.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.*

import java.net.Socket


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val client = OkHttpClient()
        var serverHost = ""
        binding.serverHostEditText.addTextChangedListener{ //에딧텍스트에 글자를 입력할때마다 변경이 됨
            serverHost = it.toString()
        }

        binding.confirmButton.setOnClickListener {
            val request: Request = Request.Builder()
                .url("http://$serverHost:8080")   //에뮬 테스트
                .build()


            val callback = object : Callback { //Callback 은 쓰레드를 새로만듬
                override fun onFailure(call: Call, e: IOException) {
                    //요청에 실패했을때
                    runOnUiThread{
                        Toast.makeText(this@MainActivity,"수신에 실패했습니다",Toast.LENGTH_SHORT).show()
                        Log.e("Client", e.toString())

                    }

                }

                override fun onResponse(call: Call, response: Response) {
                    //요청에 성공했을때
                    if (response.isSuccessful) {
                        val response = response.body?.string()
                        runOnUiThread{
                            binding.informationTextView.apply{
                                isVisible = true
                                text=response
                                //요청에 성공하고 데이터도 성공적으로 받아올때
                            }
                            binding.serverHostEditText.isVisible = false
                            binding.confirmButton.isVisible = false

                        }



                    } else {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity,"수신에 실패했습니다",Toast.LENGTH_SHORT).show()
                        }
                        Toast.makeText(this@MainActivity,"수신에 실패했습니다",Toast.LENGTH_SHORT).show()
                    }
                }

            }

            client.newCall(request).enqueue(callback)// 큐로 쌓아놨다가 넣는순서대로 동작 처리 하고 onFail 이나 onResponse 로 데이터 넘겨주게됨

        }
        binding.informationTextView




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