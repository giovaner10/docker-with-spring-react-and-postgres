import React from 'react'
import {Navigate} from 'react-router-dom'
import useGlobalLanguage from '../context/user/user.context'

export function PrivateRoute({Screen}) {
  const [user] = useGlobalLanguage()

  return user ? <Screen /> : <Navigate to="/login" />
}
