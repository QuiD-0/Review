window.onload = function () {
  const id = location.href.split('?')[1];
  getDetail(id)
}

function getDetail(id) {
  axios.get('/api/review/' + id)
  .then(function (response) {
    let data = response.data;
    renderDetailPage(data);
  })
}

function renderDetailPage(data) {
  let container = document.getElementById('detail');
  let title = document.createElement('h1');
  let author = document.createElement('h2');
  let rating = document.createElement('h3');
  let content = document.createElement('p');
  data.imgList.forEach(function (imgSrc) {
    let img = document.createElement('img');
    img.src = imgSrc;
    container.appendChild(img);
  });

  title.innerText = data.title;
  author.innerText = "by " + data.author;
  rating.innerText = "rating: " + data.rating;
  content.innerText = data.description;

  container.appendChild(title);
  container.appendChild(author);
  container.appendChild(rating);
  container.appendChild(content);
}

const downloadOriginalImages = () => {
  const id = location.href.split('?')[1];
  axios.get('/image/' + id,
      headers = {
        responseType: 'blob'
      })
  .then(function (response) {
    let data = response.data;
    console.log(data);
  })
}
