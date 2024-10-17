const canvas = document.getElementById('drawing-board');
const toolbar = document.getElementById('toolbar');
const ctx = canvas.getContext('2d');

const canvasOffsetX = canvas.offsetLeft;
const canvasOffsetY = canvas.offsetTop;

canvas.width = window.innerWidth - canvasOffsetX;
canvas.height = window.innerHeight - canvasOffsetY;

let isPainting = false;
let lineWidth = 5;
let startX;
let startY;
let oldX;
let oldY;

toolbar.addEventListener('click', e => {
    if (e.target.id === 'clear') {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
    }
});

toolbar.addEventListener('change', e => {
    if(e.target.id === 'stroke') {
        ctx.strokeStyle = e.target.value;
    }

    if(e.target.id === 'lineWidth') {
        lineWidth = e.target.value;
    }
    
});

function handleDraw(xCor, yCor, xStart, yStart){
    console.log(">>> i'm drawing", xCor, yCor, xStart, yStart);
    if(xStart && yStart){
        ctx.moveTo(xStart, yStart)
    }
    ctx.lineWidth = lineWidth;
    ctx.lineCap = 'round';

    ctx.lineTo(xCor, yCor);
    ctx.stroke();
    
    oldX = xCor;
    oldY = yCor;
}
const draw = (e) => {
    
    if(!isPainting) {
        return;
    }
    sendData((e.clientX - canvasOffsetX) + ";" + e.clientY + ";" + oldX + ";" + oldY);
   
    handleDraw(e.clientX - canvasOffsetX, e.clientY, oldX, oldY);
    
   
}

canvas.addEventListener('mousedown', (e) => {
    isPainting = true;
    startX = e.clientX;
    startY = e.clientY;
    oldX = e.clientX - canvasOffsetX;
    oldY = e.clientY;
});

canvas.addEventListener('mouseup', e => {
    isPainting = false;
    ctx.stroke();
    ctx.beginPath();
});

canvas.addEventListener('mousemove', draw);
