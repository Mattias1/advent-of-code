#
# The hexagon coordinate system:
#
#   Z
#    \  n   /
#  nw +----+ ne
#    /      \
# --+        +-- Y
#    \      /
#  sw +----+ se
#    /  s   \
#   X

N = 'n'
NE = 'ne'
SE = 'se'
S = 's'
SW = 'sw'
NW = 'nw'

class HexCoord:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y

    @property
    def z(self):
        return -self.x - self.y

    def __str__(self):
        return '({}, {}, {})'.format(self.x, self.y, self.z)

    def move(self, d):
        dirs = {
            N: (-1, 0, 1),
            NE: (-1, 1, 0),
            SE: (0, 1, -1),
            S: (1, 0, -1),
            SW: (1, -1, 0),
            NW: (0, -1, 1)
        }
        dx, dy, dz = dirs[d]
        self.x += dx
        self.y += dy

    def distanceToCenter(self):
        return max(map(abs, [self.x, self.y, self.z]))


def part1(input):
    directions = input.split(',')
    c = HexCoord(0, 0, 0)
    for d in directions:
        c.move(d)
    return c.distanceToCenter()


def part2(input):
    distance = 0
    directions = input.split(',')
    c = HexCoord(0, 0, 0)
    for d in directions:
        c.move(d)
        distance = max(distance, c.distanceToCenter())
    return distance


# Tests for part 1
def test1(input, expected):
    answer = part1(input)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(input, expected, answer))

test1('ne,ne,ne', 3)
test1('ne,ne,sw,sw', 0)
test1('ne,ne,s,s', 2)
test1('se,sw,se,sw,se', 3)
test1('n,n,s', 1)
test1('nw,nw,se', 1)


# Tests for part 2
def test2(input, expected):
    answer = part2(input)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(input, expected, answer))

test2('ne,ne,ne', 3)
test2('ne,ne,sw,sw,ne,ne,sw,sw', 2)
test2('ne,ne,ne,ne,sw,sw,sw,sw', 4)
test2('ne,ne,s,s', 2)
test2('se,sw,se,sw,se', 3)
test2('n,n,s', 2)
test2('nw,nw,se', 2)


# The real thing
with open('input11.txt') as fd:
    real_input = fd.read()[:-1]
answer = part2(real_input)
print('The answer is: {}'.format(answer))
