import {createBrowserRouter} from 'react-router-dom'
import {PrivateRoute} from './private-route.componet'
import {LoginScreen, PrincipalScreen, BuscaScreen} from '../screens'
import {DemoPostScreen} from '../screens/demo-post/demo-post.screen'

export const router = createBrowserRouter([
  {
    path: '/eu',
    element: <PrivateRoute Screen={PrincipalScreen} />,
  },
  {
    path: '/busca',
    element: <PrivateRoute Screen={BuscaScreen} />,
  },
  {
    path: '/demopost',
    element: <PrivateRoute Screen={DemoPostScreen} />,
  },
  {
    path: '/',
    element: <LoginScreen />,
  },
])
