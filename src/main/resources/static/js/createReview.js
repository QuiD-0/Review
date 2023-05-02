async function createReviewRequest() {
  if (!confirm('저장하시겠습니까?')) {
    return
  }
  let param = getFormData();
  await axios.post('http://localhost:9900/api/review', param, {
    headers: {
      'Content-Type': 'multipart/form-data',
    }
  }).then(function (response) {
    alert('저장되었습니다.');
    console.log(response);
  }).catch(function (error) {
    alert('저장에 실패했습니다.' + error);
    console.log(error);
  });
}

function validate(title, description, images, author, score) {
  if (title === '') {
    alert('제목을 입력해주세요.');
    return;
  }
  if (description === '') {
    alert('내용을 입력해주세요.');
    return;
  }
  if (images.length > 3) {
    alert('이미지는 최대 3개까지 등록 가능합니다.');
    return;
  }
  if (score === '') {
    alert('평점을 입력해주세요.');
    return;
  }
  if (author === '') {
    alert('작성자를 입력해주세요.');
    return;
  }
}

function getFormData() {
  const title = document.getElementById('title').value;
  const description = document.getElementById('description').value;
  const imgList = document.getElementById('image').files;
  const score = document.getElementById('score').value;
  const author = document.getElementById('author').value;
  const productId = Math.floor(Math.random() * 10) + 1;

  validate(title, description, imgList, score, author, productId);

  const formData = new FormData();
  formData.append('title', title);
  formData.append('description', description);
  for (let i = 0; i < imgList.length; i++) {
    formData.append('imgList', imgList[i]);
  }
  formData.append('score', score);
  formData.append('author', author);
  formData.append('productId', productId);
  return formData;
}