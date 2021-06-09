import { model, Model, Schema, Document } from "mongoose"

export interface Item extends Document {
  nickname: string
  description: string
  photo: string
  quantity: number
  monetaryValue: number,
}

const ItemSchema: Schema = new Schema({
  nickname: String,
  description: String,
  photo: String,
  quantity: Number,
  monetaryValue: Number
})

export interface Container extends Document {
  nickname: string
  description: string
  items: Item[]
}

const ContainerSchema: Schema = new Schema({
  nickname: String,
  description: String,
  items: [ItemSchema]
})

export interface Space extends Document {
  nickname: string
  description: string
  containers: Container[]
}

const SpaceSchema: Schema = new Schema({
  nickname: String,
  description: String,
  containers: [ContainerSchema]
})

export interface Property extends Document {
  nickname: string
  address: string
  spaces: Space[]
}

const PropertySchema: Schema = new Schema({
  nickname: String,
  address: String,
  spaces: [SpaceSchema]
})

export interface User extends Document {
  username: string
  firstname: string
  lastname: string
  properties: Property[]
}

const UserSchema: Schema = new Schema({
  username: String,
  firstname: String,
  lastname: String,
  properties: [PropertySchema]
})

export const UserModel: Model<User> = model('User', UserSchema)
