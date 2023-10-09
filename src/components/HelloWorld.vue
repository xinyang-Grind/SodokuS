<template>

  <div id=building>
  <div class="grid-container-group">
    <div v-for="(grid, gridIndex) in grids.slice(0,3)" :key="gridIndex">
      <div class="grid-container">
        <div v-for="(row, rowIndex) in grid" :key="rowIndex" class="grid-row">
          <div v-for="(cell, cellIndex) in row" :key="cellIndex" class="grid" :style="{ 'background-color': cellcolor[gridIndex][rowIndex][cellIndex] }">
            <input v-model="grids[gridIndex][rowIndex][cellIndex]" :class="{ 'black-text': grid1[gridIndex][rowIndex][cellIndex] !== 0, 'red-text': grid1[gridIndex][rowIndex][cellIndex] === 0 }" :readonly="grid1[gridIndex][rowIndex][cellIndex] !== 0" type="text" min="0" max="9" style="width: 100%; height: 100%; font-size: 2px;"/>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="grid-container-group">
    <div v-for="(grid, gridIndex) in grids.slice(3,6)" :key="gridIndex">
      <div class="grid-container">
        <div v-for="(row, rowIndex) in grid" :key="rowIndex" class="grid-row">
          <div v-for="(cell, cellIndex) in row" :key="cellIndex" class="grid" :style="{ 'background-color': cellcolor[gridIndex][rowIndex][cellIndex] }">
            <input v-model="grids[gridIndex][rowIndex][cellIndex]" :class="{ 'black-text': grid1[gridIndex][rowIndex][cellIndex] !== 0, 'red-text': grid1[gridIndex][rowIndex][cellIndex] === 0 }" :readonly="grid[rowIndex][cellIndex] !== 0" type="text" min="0" max="9" style="width: 100%; height: 100%; font-size: 2px;"/>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="grid-container-group">
    <div v-for="(grid, gridIndex) in grids.slice(6,9)" :key="gridIndex">
      <div class="grid-container">
        <div v-for="(row, rowIndex) in grid" :key="rowIndex" class="grid-row">
          <div v-for="(cell, cellIndex) in row" :key="cellIndex" class="grid" :style="{ 'background-color': cellcolor[gridIndex][rowIndex][cellIndex] }">
            <input v-model="grids[gridIndex][rowIndex][cellIndex]" :class="{ 'black-text': grid1[gridIndex][rowIndex][cellIndex] !== 0, 'red-text': grid1[gridIndex][rowIndex][cellIndex] === 0 }" :readonly="grid[rowIndex][cellIndex] !== 0" type="text" min="0" max="9" style="width: 100%; height: 100%; font-size: 2px;" />
          </div>
        </div>
      </div>
    </div>
  </div>
    <button @click="submit" class="custom-button">Submit</button>
    <br>
    <button @click="getNew" class="custom-button1">New Game</button>
    <br>
    <button @click="resetGrid" class="custom-button2">Reset Sudoku</button>
    <br>
    <button @click="show" class="custom-button3">Show</button>
  </div>

</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      grids: [
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]]
             ],
      grid1: [
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]]
             ],
        ans: [
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]]
             ],
        cellcolor: [
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]],
             [[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]]
             ],
      showData:"",
    }
  },
  created:function(){
   axios.get("http://localhost:8014/SudokuGenerator").then((response)=>{

      const data = response.data; // 假设返回的是一个81x9数组

    for (let i = 0; i < 9; i++) {
      // 将数据分割成9个部分，每个部分都是一个9x9数组
      const gridData = data.slice(i * 9, (i + 1) * 9);
      this.grids[i] = gridData.map(row => [...row]);
      this.grid1[i] = gridData.map(row => [...row]);
      this.ans[i] = gridData.map(row => [...row]);
      this.grids[i][1][1] = 0, this.grid1[i][1][1] = 0,
      this.grids[i][3][1] = 0, this.grid1[i][3][1] = 0,
      this.grids[i][2][6] = 0, this.grid1[i][2][6] = 0,
      this.grids[i][6][8] = 0, this.grid1[i][6][8] = 0,
      this.grids[i][4][3] = 0, this.grid1[i][4][3] = 0,
      this.grids[i][7][6] = 0, this.grid1[i][7][6] = 0,
      this.grids[i][6][2] = 0, this.grid1[i][6][2] = 0,
      this.grids[i][8][8] = 0, this.grid1[i][8][8] = 0,
      this.grids[i][0][0] = 0, this.grid1[i][0][0] = 0,
      this.grids[i][3][7] = 0, this.grid1[i][3][7] = 0,
      this.grids[i][5][3] = 0, this.grid1[i][5][3] = 0,
      this.grids[i][5][0] = 0, this.grid1[i][5][0] = 0,
      this.grids[i][4][6] = 0, this.grid1[i][4][6] = 0,
      this.grids[i][7][1] = 0, this.grid1[i][7][1] = 0,
      this.grids[i][0][6] = 0, this.grid1[i][0][6] = 0,
      this.grids[i][6][5] = 0, this.grid1[i][6][5] = 0
    }

    })
  },
  mounted:function(){
    
    console.log("挂载完毕")
  },
  watch: {
    grid(newData, oldData) {
      console.log(this.grids);
      console.log(this.grid1);

    }
  } ,
  methods: {
    resetGrid() {
      for (let i = 0; i < this.grids.length; i++) {
        for (let j = 0; j < this.grids[i].length; j++) {
          for (let k = 0; k < this.grids[i][j].length; k++)
            this.cellcolor[i][j][k]='white'
        }
      }
      for (let i = 0; i < this.grids.length; i++) {
        for (let j = 0; j < this.grids[i].length; j++) {
          for (let k = 0; k < this.grids[i][j].length; k++)
            if(this.grid1[i][j][k]==0)this.grids[i][j][k] = 0
        }
      }
    this.$forceUpdate();
    },
    submit() {
      for (let i = 0; i < this.grids.length; i++) {
        for (let j = 0; j < this.grids[i].length; j++) {
          for (let k = 0; k < this.grids[i][j].length; k++)
            if(this.grids[i][j][k] != this.ans[i][j][k])this.cellcolor[i][j][k]='red'
        }
      }
    this.$forceUpdate();
    },  
    show() {
      for (let i = 0; i < this.grids.length; i++) {
        for (let j = 0; j < this.grids[i].length; j++) {
          for (let k = 0; k < this.grids[i][j].length; k++)
            this.cellcolor[i][j][k]='white', this.grids[i][j][k] = this.ans[i][j][k]
        }
      }
    this.$forceUpdate();      
    },  
    getNew() {
      this.$forceUpdate();  
    }
  }

}
</script>

<style scoped>
#building {
  background: url("../assets/back1.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: contain; /* 使用 cover 来保持图片比例并覆盖整个视口 */
  background-position: center; /* 让背景图像在视口中居中 */
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
  align-items: center;
}
.grid-container {
  display: grid;
  grid-template-columns: repeat(9, 14px);
  grid-template-rows: repeat(9, 14px);
  gap: 1px;
  margin-left: 100px;
  margin-top: -90px;
  margin-bottom: 105px;
}
.grid-container-group {
  display: flex;
  flex-direction: column; /* 垂直排列，每个容器在新的一行 */
    margin-right: -90px; /* 调整列之间的间距 */
    margin-top: 100px;
    transform: translateX(-50px); /* 使用transform属性来实现向左平移 */
}

.custom-button {
    display: flex;
  flex-direction: column;
   align-items: center; /* 可以居中按钮 */
  background-color: #3498db; /* 按钮背景颜色 */
  color: #fff; /* 文本颜色 */
  padding: 20px 65px; /* 内边距 */
  border: none; /* 移除边框 */
  border-radius: 5px; /* 圆角 */
  cursor: pointer; /* 鼠标样式 */
  font-size: 18px; /* 字体大小 */
  transition: background-color 0.3s ease; /* 鼠标悬停过渡效果 */
  margin-left: 100px;
    margin-top: -400px;
  /* 鼠标悬停时的样式 */
  &:hover {
    background-color: #2980b9;
  }
}
.custom-button1 {
  background-color: #3498db; /* 按钮背景颜色 */
  color: #fff; /* 文本颜色 */
  padding: 20px 50px; /* 内边距 */
  border: none; /* 移除边框 */
  border-radius: 5px; /* 圆角 */
  cursor: pointer; /* 鼠标样式 */
  font-size: 18px; /* 字体大小 */
  transition: background-color 0.3s ease; /* 鼠标悬停过渡效果 */
  margin-left: -185px;
    margin-top: -200px;
  /* 鼠标悬停时的样式 */
  &:hover {
    background-color: #2980b9;
  }
}
.custom-button2 {
  background-color: #3498db; /* 按钮背景颜色 */
  color: #fff; /* 文本颜色 */
  padding: 20px 40px; /* 内边距 */
  border: none; /* 移除边框 */
  border-radius: 5px; /* 圆角 */
  cursor: pointer; /* 鼠标样式 */
  font-size: 18px; /* 字体大小 */
  transition: background-color 0.3s ease; /* 鼠标悬停过渡效果 */
  margin-left: -190px;

  /* 鼠标悬停时的样式 */
  &:hover {
    background-color: #2980b9;
  }
}
.custom-button3 {
  background-color: #3498db; /* 按钮背景颜色 */
  color: #fff; /* 文本颜色 */
  padding: 20px 40px; /* 内边距 */
  border: none; /* 移除边框 */
  border-radius: 5px; /* 圆角 */
  cursor: pointer; /* 鼠标样式 */
  font-size: 18px; /* 字体大小 */
  transition: background-color 0.3s ease; /* 鼠标悬停过渡效果 */
  margin-left: -195px;
    margin-top: 200px;
  /* 鼠标悬停时的样式 */
  &:hover {
    background-color: #2980b9;
  }
}
.grid {
  border: 1px solid #ccc;
  width: 14px;
  height: 14px;
  text-align: center;
  line-height: 14px;
  font-size: 1px;
}

input[type="text"] {
  border: none;
  width: 100%;
  height: 100%;
  font-size: 1px;
  text-align: center;
  background-color: transparent;
  outline: none;
}

.black-text {
  color: gray; 
}

.red-text {
  color: black; 
}

</style>
</script>
