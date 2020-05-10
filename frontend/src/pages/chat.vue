<template>
    <div>
        <input v-model="msg" @keyup.enter="send">
        <div v-for="(msg, index) in receiveMessageList" :key="index">
          {{ msg }}
        </div>
    </div>
</template>
<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  data() {
    return {
      msg: null,
      receiveMessageList: []
    }
  },
  mounted() {
    this.connect()
  },
  methods: {
    connect() {
      const serverURL = 'http://localhost:8081/web'
      let socket = new SockJS(serverURL)
      this.stompClient = Stomp.over(socket)
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true
          console.log('소켓 연결 성공', frame)
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          let id = this.stompClient.subscribe('/topic/public', res => {
            this.receiveMessageList.push(JSON.parse(res.body))
            console.log('응답 값', res.body)
          }).id

          console.log('구독 id', id)
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error)
          this.connected = false
        }
      )
    },
    send() {
      console.log(this.msg)
      this.stompClient.send('/chat.sendMessage', this.msg)
      this.msg = ''
    }
  }
}
</script>
