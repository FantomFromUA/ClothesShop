import {makeAutoObservable} from "mobx"

export default class User{
  constructor(){
    this._types =
    [
      {id:1, name:"Футболки"},
      {id:2, name:"Джинси"},
      {id:3, name:"Світшоти"},
      {id:4, name:"Штани"},
    ]

    this._sizes =
    [
      {id:1, name:"XS"},
      {id:2, name:"S"},
      {id:3, name:"M"},
      {id:4, name:"L"},
      {id:5, name:"XL"},
      {id:6, name:"XXL"},
    ]

    this._clothes= 
    [
      {id:1, name:"Футболка чорна", price: 400, img:"https://media2.symbol.ua/aio-images/9c/a4/9ca45ef689db2c967f5cc4f5f3bcfeaa/b718eb6d-b8d4-427b-ac67-48d80ab1a0e7.jpg"},
      {id:2, name:"Футболка чорна", price: 400, img:"https://media2.symbol.ua/aio-images/9c/a4/9ca45ef689db2c967f5cc4f5f3bcfeaa/b718eb6d-b8d4-427b-ac67-48d80ab1a0e7.jpg"},
      {id:3, name:"Футболка чорна", price: 400, img:"https://media2.symbol.ua/aio-images/9c/a4/9ca45ef689db2c967f5cc4f5f3bcfeaa/b718eb6d-b8d4-427b-ac67-48d80ab1a0e7.jpg"},
      {id:4, name:"Футболка чорна", price: 400, img:"https://media2.symbol.ua/aio-images/9c/a4/9ca45ef689db2c967f5cc4f5f3bcfeaa/b718eb6d-b8d4-427b-ac67-48d80ab1a0e7.jpg"},
      {id:5, name:"Футболка чорна", price: 400, img:"https://media2.symbol.ua/aio-images/9c/a4/9ca45ef689db2c967f5cc4f5f3bcfeaa/b718eb6d-b8d4-427b-ac67-48d80ab1a0e7.jpg"},
      {id:6, name:"Футболка чорна", price: 400, img:"https://media2.symbol.ua/aio-images/9c/a4/9ca45ef689db2c967f5cc4f5f3bcfeaa/b718eb6d-b8d4-427b-ac67-48d80ab1a0e7.jpg"},
    ]

    this._selectedType = {}

    this._selectedSize = {}

    makeAutoObservable(this)
  }

  setTypes(types){
    this._types = types
  }
  
  setSizes(sizes){
    this._sizes = sizes
  }
  setClothes(clothes){
    this._clothes = clothes
  }
  setSelectedType(type){
    this._selectedType = type
  }

  setSeceltedSize(size){
    this._selectedSize = size
  }

  get types(){
    return this._types
  }

  get sizes(){
    return this._sizes
  }

  get clothes(){
    return this._clothes
  }
  get selectedType(){
    return this._selectedType
  }
  get selectedSize(){
    return this._selectedSize
  }
}