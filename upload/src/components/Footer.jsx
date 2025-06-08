import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

const Footer = () => {
  return (
    <footer className="footer bg-dark text-white">
      <Container>
        <Row>
          <Col className="text-center py-3">
            Copyright © {new Date().getFullYear()} FlyHigh Airlines
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;