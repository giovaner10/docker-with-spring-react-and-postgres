import './input.component.css'
import React from 'react'

export function InputComponent({
  label,
  type,
  id,
  name,
  handleChange,
  value,
  placeholder,
}) {
  return (
    <>
      <label htmlFor={name} className="login_label">
        {label}
      </label>
      <input
        type={type}
        className="login_input"
        id={id}
        name={name}
        defaultValue={value}
        onChange={handleChange}
        placeholder={placeholder}
      />
    </>
  )
}
