const modalLogOpen = document.querySelector('#loginModal');
const modalJoinOpen = document.querySelector('#joinModal');
const bg = document.querySelector('.bg');
const loginForm = document.querySelector('#login');
const joinForm = document.querySelector('#join');

const close_btn = document.querySelector('.close_btn');

//로그인 모달창
modalLogOpen.addEventListener('click', () => {
    bg.style.display = 'block';
    loginForm.style.display = 'block';
})

close_btn.addEventListener('click', () => {
    bg.style.display = 'none';
    loginForm.style.display = 'none';
})

bg.addEventListener('click', () => {
    bg.style.display = 'none';
    loginForm.style.display = 'none';
})

//join 모달창
modalJoinOpen.addEventListener('click', () => {
    bg.style.display = 'block';
    joinForm.style.display = 'block';
})

close_btn.addEventListener('click', () => {
    bg.style.display = 'none';
    joinForm.style.display = 'none';
})

bg.addEventListener('click', () => {
    bg.style.display = 'none';
    joinForm.style.display = 'none';
})