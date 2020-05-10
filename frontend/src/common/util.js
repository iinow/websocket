import $ from 'jquery'

async function getIpAddress() {
  let ipAddr = undefined
  await $.getJSON("https://api.ipify.org/?format=json", function(e) {
    ipAddr = e.ip
  })
  console.log('ip', ipAddr)
  return ipAddr
}

export default {
  getIpAddress
}
