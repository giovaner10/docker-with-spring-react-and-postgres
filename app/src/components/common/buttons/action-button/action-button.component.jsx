import './action-button.component.css'
import React from 'react'

export function ActionButtonComponent({
  actionClick,
  textButton,
  param,
  screenComponent,
}) {
  return (
    <>
      {param && actionClick ? (
        <button className="action_button" onClick={() => actionClick(param)}>
          {textButton}
        </button>
      ) : actionClick ? (
        <button className="action_button" onClick={() => actionClick(param)}>
          {textButton}
        </button>
      ) : (
        <button className={`action_button ${screenComponent}`}>
          {textButton}
        </button>
      )}
    </>
  )
}
