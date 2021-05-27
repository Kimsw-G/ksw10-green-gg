let tds = document.getElementsByTagName("td");

for (const td of tds) {
    if(td.className>12){
        td.style.backgroundColor = "#FF0000";
    }else if(td.className>9){
        td.style.backgroundColor = "#FF4000";
    }else if(td.className>6){
        td.style.backgroundColor = "#FF8000";
    }else if(td.className>3){
        td.style.backgroundColor = "#FFBF00";
    }else if(td.className>0){
        td.style.backgroundColor = "#FFFF00";
    }
}

let tdElm = document.getElementsByTagName("td");
let pElm = document.getElementsByTagName("p");
for(let i = 0;i<tdElm.length;i++){
    tdElm[i].addEventListener('mouseover', function() {
        pElm[i].style.display = 'block';
    })
    tdElm[i].addEventListener('mouseout', function() {
        pElm[i].style.display = 'none';
    })
}
