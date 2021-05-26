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