let input = document.querySelector('#img');
function imgsubmit(){
    if(input.files.length == 0){
        return false;
    }
    return true;
}