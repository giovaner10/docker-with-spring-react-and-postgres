import './toastr.component.css'
import React, {useEffect} from 'react'
import useGlobalToastr from '../../../context/toastr/toastr.context'

const TOASTR_DELAY = 4500
export default function ToastrComponent() {
  const [toastr, setMessagem] = useGlobalToastr()

  useEffect(() => {
    if (toastr) {
      setTimeout(() => {
        setMessagem({})
      }, TOASTR_DELAY)
    }
  }, [toastr, setMessagem])

  if (!toastr.messagem) return

  return (
    <div className={`toastr ${toastr.type}`}>
      <p className="toastr_messagem">{toastr.messagem}</p>
    </div>
  )
}
