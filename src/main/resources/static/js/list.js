window.onload = function() {
  getList();
}
function getList() {
  axios.get('/api/review/list')
  .then(function (response) {
    let list = response.data;
    renderList(list);
  })
}
function renderList(list) {
  let container = document.getElementById('reviewList');
  container.innerHTML = '';
  for (let i = 0; i < list.length; i++) {
    let tr = document.createElement('tr');
    let td1 = document.createElement('td');
    let td2 = document.createElement('td');
    let td3 = document.createElement('td');
    let td4 = document.createElement('td');
    tr.setAttribute('onclick', 'location.href="/review/' + list[i].id + '"');
    tr.setAttribute('class', "detail");
    td1.innerText = list[i].title;
    td2.innerText = list[i].author;
    td3.innerText = list[i].score;
    td4.innerText = list[i].createAt;
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    container.appendChild(tr);
  }
}