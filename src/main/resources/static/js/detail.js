window.onload = function() {
    const id = location.href.split('?')[1];
    getDetail(id)
}

function getDetail(id) {
    axios.get('/api/review/' + id)
        .then(function (response) {
            let data = response.data;
            renderDetail(data);
        })
}

function renderDetail(data) {
    let container = document.getElementById('detail');
    let title = document.createElement('h1');
    let author = document.createElement('h2');
    let rating = document.createElement('h3');
    let content = document.createElement('p');
    let img = document.createElement('img');
    img.setAttribute('src', data.imgList[0]);
    title.innerText = data.title;
    author.innerText = data.author;
    rating.innerText = data.rating;
    content.innerText = data.description;
    container.appendChild(img);
    container.appendChild(title);
    container.appendChild(author);
    container.appendChild(rating);
    container.appendChild(content);
}