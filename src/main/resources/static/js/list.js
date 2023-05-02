window.onload = function() {
  getList();
}

function getList() {
  axios.get('/api/review/list')
  .then(function (response) {
    console.log(response);
  })
}
