def part1(size, lengths):
    pos = 0
    skipsize = 0
    result = list(range(size))
    result, _, _ = round(result, lengths, pos, skipsize)
    return result[0] * result[1]


def round(result, lengths, pos, skipsize):
    for l in lengths:
        result = reversePart(result, pos, l)
        pos = (pos + skipsize + l) % len(result)
        skipsize += 1
    return (result, pos, skipsize)


def reversePart(result, start, length):
    rcopy = result[:]
    double = result + result
    replacements = double[start : start + length][::-1]
    for i in range(len(replacements)):
        result[(start + i) % len(result)] = replacements[i]
    # print(rcopy, start, length, double[start : start + length], replacements, result)
    return result


def fromAscii(c):
    if c == ',':
        return 44
    return int(c) + 48


def part2(rawinput):
    # Sparse hash
    sparse = list(range(256))
    lengths = map(fromAscii, rawinput) + [17, 31, 73, 47, 23]
    pos = 0
    skipsize = 0
    for r in range(64):
        sparse, pos, skipsize = round(sparse, lengths, pos, skipsize)
    # Dense hash
    dense = [0] * 16
    for i, v in enumerate(sparse):
        dense[i / 16] ^= v
    return ''.join(map(toHex, dense))


def toHex(b):
    h = '0123456789abcdef'
    return h[b >> 4] + h[b % 16]


# Tests for part 1
def test1(size, lengths, expected):
    answer = part1(size, lengths)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(lengths, expected, answer))

test1(5, [3, 4, 1 ,5], 12)


# Tests for part 2
def test2(rawinput, expected):
    answer = part2(rawinput)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(rawinput, expected, answer))

test2('', 'a2582a3a0e66e6e86e3812dcb672a272')
test2('1,2,3', '3efbe78a8d82f29979031a4aa0b16a9d')
test2('1,2,4', '63960835bcdc130f0b66d7ff4f6a5a8e')


# The real thing
# real_input = [199,0,255,136,174,254,227,16,51,85,1,2,22,17,7,192]
# answer = part1(256, real_input)
real_input = '199,0,255,136,174,254,227,16,51,85,1,2,22,17,7,192'
answer = part2(real_input)
print('The answer is: {}'.format(answer))
