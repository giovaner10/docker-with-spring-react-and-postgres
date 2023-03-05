import {RouterProvider} from 'react-router-dom'
import './App.css'
import {router} from './router'
import {GlobalUserProvider} from './context/user/user.context'
import {GlobalToastrProvider} from './context/toastr/toastr.context'
import Toastr from './components/common/toastr/toastr.component'

function App() {
  return (
    <div className="App">
      <GlobalToastrProvider>
        <GlobalUserProvider>
          <RouterProvider router={router} />
          <Toastr />
        </GlobalUserProvider>
      </GlobalToastrProvider>
    </div>
  )
}

export default App
