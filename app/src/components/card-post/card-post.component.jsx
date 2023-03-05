import * as React from 'react'
import {styled} from '@mui/material/styles'
import Card from '@mui/material/Card'
import CardHeader from '@mui/material/CardHeader'
import CardMedia from '@mui/material/CardMedia'
import CardContent from '@mui/material/CardContent'
import CardActions from '@mui/material/CardActions'
import Collapse from '@mui/material/Collapse'
import Avatar from '@mui/material/Avatar'
import IconButton from '@mui/material/IconButton'
import Typography from '@mui/material/Typography'
import {red} from '@mui/material/colors'
import FavoriteIcon from '@mui/icons-material/Favorite'
import ShareIcon from '@mui/icons-material/Share'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore'
import MoreVertIcon from '@mui/icons-material/MoreVert'
import {useState, useEffect} from 'react'
import {findComentsPost} from '../../api/acess-user/find-coments.api'
import {LoadComponent} from '../common/load-page/load-page.component'
import {responsivePropType} from '@mui/system'

const ExpandMore = styled(props => {
  const {expand, ...other} = props
  return <IconButton {...other} />
})(({theme, expand}) => ({
  transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
  marginLeft: 'auto',
  transition: theme.transitions.create('transform', {
    duration: theme.transitions.duration.shortest,
  }),
}))

export function CardPostComponent({post}) {
  const [expanded, setExpanded] = useState(false)
  const [coments, setComents] = useState()

  const handleExpandClick = () => {
    setExpanded(!expanded)
  }

  async function getComents() {
    try {
      const response = await findComentsPost()
      setComents(response)
    } catch (error) {}
  }

  React.useEffect(() => {
    getComents()
  }, [])

  return (
    <Card sx={{maxWidth: 345}}>
      <CardHeader
        avatar={
          <Avatar sx={{bgcolor: red[500]}} aria-label="recipe">
            <img src={post.fotoUsuario} />
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={post.nomeUsuario}
        subheader={post.data}
      />
      <CardMedia
        component="img"
        height="194"
        image={post.foto}
        alt={post.legeneda}
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {post.legenda}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <div style={{height: 130, overflowY: 'scroll'}}>
            {coments ? (
              coments.map(c => (
                <CardHeader
                  key={c.id}
                  avatar={
                    <Avatar sx={{bgcolor: red[500]}} aria-label="recipe">
                      <img src={c.fotoUsuario} />
                    </Avatar>
                  }
                  title={c.comentario}
                  subheader={c.nomeUsuario}
                />
              ))
            ) : (
              <LoadComponent />
            )}
          </div>
        </CardContent>
      </Collapse>
    </Card>
  )
}
