import './link-button.component.css'
import {Link} from 'react-router-dom'

export function LinkButtonComponent({path, text, screenComponent}) {
  return (
    <Link className={`link_button ${screenComponent}`} to={path}>
      {text}
    </Link>
  )
}
