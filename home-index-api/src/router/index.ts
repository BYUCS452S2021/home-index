import { Router } from 'express'
import { User, UserModel } from '../model/user.schema'

const router = Router()

router.route('/user/:username')
  .get(async (req, res) => {
    try {
      const user: User | null = await UserModel.findOne({ username: req.params.username })
      res.send(user)
    } catch (e) {
      console.log(e)
      res.status(500).send(e.message)
    }
  })

  .post(async (req, res) => {
    try {
      let user = await UserModel.findOne({ username: req.params.username })
      if (user) {
        user.set(req.body)
        user.save()
        res.send(user)
      } else {
        let newUser = await UserModel.create(req.body)
        res.send(newUser)
      }
    } catch (e) {
      console.log(e)
      res.status(500).send(e.message)
    }
  })

  .delete(async (req, res) => {
    try {
      const response = await UserModel.findOneAndDelete({ username: req.params.username })
      res.send(response)
    } catch (e) {
      console.log(e)
      res.status(500).send(e.message)
    }
  })

export { router }
