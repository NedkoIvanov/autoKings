const mechanicId = document.getElementById('mechanicId').value;

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const commentForm = document.getElementById('commentForm');
commentForm.addEventListener("submit", handleCommentSubmit);

const commentsCtnr = document.getElementById('commentSect');

const allComments = [];

const displayComments = (comments) => {
  commentsCtnr.innerHTML = comments.map((c) => {
    return asComment(c);
  }).join('');
};

async function handleCommentSubmit(event) {
  event.preventDefault();

  const form = event.currentTarget;
  const url = form.action;

const formData = new FormData();
formData.append('textContent', form.elements.message.value);


  try {
    const responseData = await postFormDataAsJson({url, formData});
    console.log(responseData)
    commentsCtnr.insertAdjacentHTML("afterbegin", asComment(responseData));
    form.reset();
  } catch (error) {
    let errorObj = JSON.parse(error.message);
    if (errorObj.errors) {
      errorObj.errors.forEach((e) => {
        let elementWithError = document.getElementById(e.field);
        if (elementWithError) {
          elementWithError.classList.add("is-invalid");
        }
      });
    }
  }

}



async function postFormDataAsJson({url, formData}) {
  const plainFormData = Object.fromEntries(formData.entries());
  const formDataAsJsonString = JSON.stringify(plainFormData);

  const fetchOptions = {
    method: "POST",
    headers: {
      [csrfHeaderName]: csrfHeaderValue,
      "Content-Type": "application/json",
      "Accept": "application/json"

    },
    body: formDataAsJsonString
  };
  const response = await fetch(url, fetchOptions);

  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage);
  }

  return response.json();
}




function asComment(c) {
  let formatter = new Intl.DateTimeFormat('en', {
    day: 'numeric',
    month: 'short',
    hour: 'numeric',
    minute: 'numeric'
  });

  let commentHtml = `<div id="commentsCtnr-${c.id}" class="card my-3" style="border: 20px solid black; border-radius: 60px; padding: 10px;">`;
  commentHtml += `<div class="d-flex align-items-center">`;
  commentHtml += `<img class="rounded-circle shadow-1-strong me-3" src=${c.authorImage} alt="avatar" width="65" height="65" />`;
  commentHtml += `<h4>${c.authorName}</h4>`;
  commentHtml += `</div>`;
  commentHtml += `<div style="border: 2px solid black; border-radius: 10px; padding: 10px;">`;
  commentHtml += `<p class="my-3">${c.textContent}</p>`;
  commentHtml += `<hr style="border-top: 1px solid black; margin: 0 0 10px 0;">`;
  commentHtml += `<p class="text-muted">${formatter.format(new Date(c.created))}</p>`;
  commentHtml += `</div>`;

  commentHtml += `</div>`;

  return commentHtml;
}


async function fetchComments(id) {
  const url = `http://localhost:8086/api/${id}/comments`;
  const response = await fetch(url);
  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage);
  }
  return response.json();
}

async function loadComments() {
  const mechanicId = document.getElementById('mechanicId').value;
  const comments = await fetchComments(mechanicId);
  displayComments(comments);
}

window.addEventListener('load', loadComments);