import React from 'react';
import { Container, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const HomePage = () => {
  return (
    <div className="hero-section text-center">
      <div style={{ background: 'rgba(0,0,0,0.4)', padding: '8rem 0' }}>
        <Container>
          <h1>Find Your Next Adventure</h1>
          <p>Book flights to destinations all over the world with FlyHigh.</p>
          <Link to="/search">
            <Button variant="primary" size="lg">
              Search Flights Now
            </Button>
          </Link>
        </Container>
      </div>
    </div>
  );
};

export default HomePage;