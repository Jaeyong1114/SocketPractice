package com.example.socketpractice


import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

fun main(){

    Thread{
        val port = 8080
        val server = ServerSocket(port)

        while(true){

            val socket = server.accept()


            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val printer = PrintWriter(socket.getOutputStream())

            var input: String? = "-1"

            while (input != null && input != "") {
                input = reader.readLine()
            }

            println("READ DATA $input")

            //HEADER
            printer.println("HTTP/1.1 200 OK ") //Http1.1 버전을 사용하겠고 200은 데이터를 정상적으로 응답
            printer.println("Content-Type: text/html\r\n") // 컨텐트타입은 text에 html 타입으로 보내주겠다  \r\n 넣어주는이유는 규격 이게 헤더부분 (보진않지만 서버와 클라이언트간 통신할때)
            // \r\n 을 넣었으므로 이뒤에 나오는  부분이 body 부분

            //BODY
            printer.println("{\"message\": \"hello World\"}") //Http 통신이므로 HTML 태그 사용 "<h1>Hello World</h1>" 에서 json으로가져올거기때문에 바꿈
            printer.println("\r\n")
            printer.flush() //flush 를통해 잔여 데이터가 있을수 있으므로 마저 배출
            printer.close() // 다 배출  후 연결끊음

            reader.close() // 읽는부분도 close

            socket.close()

        }


    }.start()

}