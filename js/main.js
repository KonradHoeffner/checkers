const ROWS = 8;
const COLS = 8;

// https://2ality.com/2020/01/enum-pattern.html
const Piece = {BLACK: 'blackPiece',WHITE:'whitePiece',EMPTY: 'empty'}

const makeRow = () => new Array(COLS).fill(Piece.EMPTY);

let selected = null;

class Cell
{
  onclick()
  {
    const piece = this.board.cells[this.row][this.col];
	this.piece = piece;
    console.log("You clicked "+this.col+" "+this.row+" with content "+this.piece);
    if(selected)
    {
      console.log("moving");
      this.piece = selected.piece;
      this.element.classList.add(selected.piece);
      this.board.cells[this.row][this.col]=selected.piece;
      this.board.cells[selected.row][selected.col]=Piece.EMPTY;
	  selected.element.classList.remove(selected.piece);
      selected.piece = Piece.EMPTY;
      selected.element.classList.remove("selected");
      selected = null;
    }
    else
    { 
      if(this.piece===Piece.EMPTY) {return;}
      this.element.classList.add("selected");
      selected = this;
   }
 }

  constructor(col,row,board,element)
  {
    this.row=row;
    this.col=col;
    this.board=board;
    this.element=element;
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
        const cell = new Cell(col,row,this,cellDiv);
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
