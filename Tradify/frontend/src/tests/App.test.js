import { render, screen } from '@testing-library/react';
import App from '../App';

test('renders learn react link', () => {
  render(<App />);
  const linkElements = screen.getAllByText(/Users/i);

  linkElements.forEach(element => {
    expect(element).toBeInTheDocument();
  });
});
