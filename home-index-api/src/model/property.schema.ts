import { model, Schema, Model, Document } from 'mongoose'
import { UserAccount } from './user-account.schema'
import { Space } from './space.schema'

export interface Property extends Document {
  nickname: string
  address: string
  owners: UserAccount[]
  spaces: Space[]
}

const PropertySchema: Schema = new Schema({
  nickname: { type: String, required: true },
  address: { type: String },
  owners: { type: Schema.Types.ObjectId, ref: 'UserAccount' },
  spaces: { type: Schema.Types.ObjectId, ref: 'Space' }
})

export const PropertyModel: Model<Property> = model('Property', PropertySchema)
