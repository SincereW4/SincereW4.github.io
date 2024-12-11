import random

def get_user_guess():
  while True:
    print('Guess the coin toss! Enter heads or tails:')
    user_guess = input()
    if user_guess.lower() in ('heads', 'tails'):
      return user_guess.lower()
    else:
      print('Invalid input. Please enter heads or tails.')

def get_coin_toss():
  toss = random.randint(0, 1)
  if toss == 0:
    return 'heads'
  else:
    return 'tails'

def play_coin_game():
  user_guess = get_user_guess()
  coin_toss = get_coin_toss()
  if user_guess == coin_toss:
    print('You got it!')
    print('The coin was ' + coin_toss + '!')
  else:
    print('Nope! The coin was ' + coin_toss + '!')

if __name__ == '__main__':
  play_coin_game()