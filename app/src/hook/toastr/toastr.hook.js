import useGlobalToastr from '../../context/toastr/toastr.context'

export function useToastr() {
  const [, setToastr] = useGlobalToastr()

  function showToastr(messagem, type) {
    setToastr({messagem, type})
  }

  return showToastr
}
