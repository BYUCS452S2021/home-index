import { model, Schema, Model, Document } from 'mongoose'

export interface Item extends Document {
  nickname: string
  description: string
  photo: string
  quantity: number
  monetaryValue: number,
}

const ItemSchema: Schema = new Schema({
  nickname: { type: String, required: true },
  description: { type: String },
  photo: { type: String },
  quantity: { type: Number },
  monetaryValue: { type: Number }
})

export const ItemModel: Model<Item> = model('Item', ItemSchema)
