const ROWS = 8;
const COLS = 8;

// https://2ality.com/2020/01/enum-pattern.html
const Cell = {BLACK: 'B',WHITE:'W',EMPTY: '_'}

const makeRow = () => new Array(COLS).fill(Cell.EMPTY);

class Board
{
  // https://2ality.com/2018/12/creating-arrays.html
  cells = Array.from({length: ROWS}, makeRow)

  element = document.createElement("div");

  constructor()
  {
    element.classList.add("board");
    for(let row = 0; row < ROWS; row+=2)
    {
      for(let col = 0; col < 2; col++)
      {
        this.cells[row+col][col]=Cell.BLACK;
        //this.cells[ROWS-row+col][col]=Cell.WHITE;
      }
    }
    for(let row = 0; row < ROWS; row++)
    {
      for(let col = 0; col < COLS; col++)
      {
        const cellDiv = document.createElement("div");
        this.element.appendChild(cellDiv);
        cellDiv.classList.add("cell");
        cellDiv.classList.add(["blackCell","whiteCell"][(col+row)%2]);
        cellDiv.classList.add("blackPiece");

      }
    }
  }
}

function main()
{
  document.getElementById("board").appendChild(new Board().element);
}
