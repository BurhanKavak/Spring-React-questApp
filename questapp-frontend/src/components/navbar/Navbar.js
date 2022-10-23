import React from "react";
import { Link } from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import PersonIcon from "@mui/icons-material/Person";
import "../../style/style.css";

function Navbar() {
  let userId = 5;
  return (
    <div>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar color="secondary" position="static">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              sx={{ mr: 2 }}
            >
              <MenuIcon />
            </IconButton>

            <Typography
              variant="h5"
              component="div"
              sx={{ flexGrow: 1, textAlign: "left" }}
            >
              <Link className="navbar-header" to="/">
                <b>Home</b>
              </Link>
            </Typography>

            <Button variant="contained" color="secondary">
              <Link
                className="navbar-header"
                to={{ pathname: "/users/" + userId }}
              >
                <PersonIcon></PersonIcon>
              </Link>
            </Button>
          </Toolbar>
        </AppBar>
      </Box>
    </div>
  );
}
export default Navbar;
