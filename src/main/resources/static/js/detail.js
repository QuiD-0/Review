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

  if(data.imgList.length > 0) {
    let downloadBtn = document.createElement('button');
    downloadBtn.innerText = "원본 다운로드";
    downloadBtn.onclick = downloadOriginalImages;
    downloadBtn.setAttribute('class', 'btn btn-primary')
    container.appendChild(downloadBtn);
  }
}

const downloadOriginalImages = () => {
  const id = location.href.split('?')[1];
  axios.get('/images/' + id,
      headers = {
        responseType: 'blob'
      })
  .then(function (response) {
    var downloadUrl = window.URL.createObjectURL(response.data);
    var link = document.createElement('a');
    link.href = downloadUrl;
    let title = response.headers['content-disposition'].split(';');
    link.download = title[1].split('=')[1];
    document.body.appendChild(link);
    link.click();
    link.parentNode.removeChild(link);
  })
}
