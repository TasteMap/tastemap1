// 로그인 모달창
var modalLogOpen = document.querySelector('#loginModal');
var modalLogClose = document.querySelector('.login-container')

const open = () => {
    document.querySelector(".login-container").classList.remove("hidden");
}

const close = () => {
    document.querySelector(".login-container").classList.add("hidden");
}

modalLogOpen.addEventListener('click', open);
modalLogClose.addEventListener('click', close);