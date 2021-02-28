const ROWS = 8;
const COLS = 8;

// https://2ality.com/2020/01/enum-pattern.html
const Piece = {BLACK: 'blackPiece',WHITE:'whitePiece',EMPTY: ''}

const makeRow = () => new Array(COLS).fill(Piece.EMPTY);

class Cell
{
 row;
 col;

 onclick()
 {
   console.log("You clicked "+this.col+" "+this.row);
 }

 constructor(col,row)
 {
   this.row=row;
   this.col=col;
   this.onclick=this.onclick.bind(this);
 }
}

class Board
{
  // https://2ality.com/2018/12/creating-arrays.html
  cells = Array.from({length: ROWS}, makeRow)

  element = document.createElement("div");
  x = 5;
  constructor()
  {
    this.element.classList.add("board");
    for(let row = 0; row < 2; row++)
    {
      for(let col = 0; col < COLS; col+=2)
      {
        this.cells[row][col+row]=Piece.BLACK;
        this.cells[ROWS-1-row][col+1-row]=Piece.WHITE;
      }
    }
    for(let row = 0; row < ROWS; row++)
    {
      for(let col = 0; col < COLS; col++)
      {
        const cellDiv = document.createElement("div");
		const cell = new Cell(col,row);
        cellDiv.addEventListener("click",cell.onclick);
		this.element.appendChild(cellDiv);
        cellDiv.classList.add("cell");
        cellDiv.classList.add(["blackCell","whiteCell"][(col+row)%2]);
		switch(this.cells[row][col])
		{
		  case Piece.BLACK: {cellDiv.classList.add("blackPiece");break;}
		  case Piece.WHITE: {cellDiv.classList.add("whitePiece");}
		}

      }
    }
  }
}

function main()
{
  document.getElementById("board").appendChild(new Board().element);
}
