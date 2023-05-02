async function createReviewRequest() {
  if(!confirm('저장하시겠습니까?')){
    return
  }
  let param = getFormData();
  const response = await axios.post('http://localhost:9900/api/review', param, {
    headers: {
      'Content-Type': 'multipart/form-data',
    }
  });

  response.then(
      () => {
        alert('리뷰가 등록되었습니다.');
        window.location.reload();
      },
      (error) => {
        alert('리뷰 등록에 실패하였습니다.'+ error);
      }
  )
}
function validate(title, content, images, author, score) {
  if (title === '') {
    alert('제목을 입력해주세요.');
    return;
  }
  if (content === '') {
    alert('내용을 입력해주세요.');
    return;
  }
  if (images.length > 3){
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
  const content = document.getElementById('content').value;
  const images = document.getElementById('image').files;
  const score = document.getElementById('score').value;
  const author = document.getElementById('author').value;
  const productId = document.getElementById('productId').value;

  validate(title, content, images, score, author, productId);

  const formData = new FormData();
  formData.append('title', title);
  formData.append('content', content);
  for (let i = 0; i < images.length; i++) {
    formData.append('images', images[i]);
  }
  formData.append('score', score);
  formData.append('author', author);
  formData.append('productId', productId);
  return formData;
}