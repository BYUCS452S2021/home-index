import { Router } from 'express'
import { readAllUsers, createUser } from '../controller/User.controller'
import { User } from '../model/item.schema'

const router = Router()

router.route('/users')
  .get(async (_, res) => {
    try {
      const users: User[] = await readAllUsers()
      res.send(users)
    } catch (e) {
      console.log(e)
      res.status(500).send(e.message)
    }
  })

  .post(async (req, res) => {
    try {
      const user = await createUser(req.body)
      res.send(user)
    } catch (e) {
      console.log(e)
      res.status(500).send(e.message)
    }
  })

export { router }
