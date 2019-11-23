const table = {};
var flag = "";
`59:87:57:51:85:80{:40:50:56:08:82:58:81:08:18:85:57:87:48:85:88:40:50:56:59:15:56:11:18:85:59:51:}`.replace(/\d+:?/g, i => {
   let number = parseInt(i)
   let value = table[number]
   if (!value) {
      value = table[number] = String.fromCharCode(65 + Object.keys(table).length)
   }
   flag += value;
})
console.log(flag)