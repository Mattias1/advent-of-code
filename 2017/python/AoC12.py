def part1(input):
    pipes = getPipes(input)
    programs_of_0 = getProgramsOf(0, pipes)
    return len(programs_of_0)


def getPipes(input):
    rows = input.split('\n')[:-1]
    return map(getPipesFromRow, rows)


def getPipesFromRow(row):
    l = row.split(' ')[2:]
    return [int(filter(lambda c: c != ',', s)) for s in l]


def getProgramsOf(nr, pipes):
    programs = set([nr])
    addToSet(programs, nr, pipes)
    return programs


def addToSet(s, program, pipes):
    for v in pipes[program]:
        if not v in s:
            s.add(v)
            addToSet(s, v, pipes)


def part2(input):
    pipes = getPipes(input)
    groups = []
    for i in range(len(pipes)):
        if not existsInGroup(i, groups):
            g = getProgramsOf(i, pipes)
            groups.append(g)
    return len(groups)


def existsInGroup(i, groups):
    for g in groups:
        if i in g:
            return True
    return False


# Tests for part 1
def test1(input, expected):
    answer = part1(input)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(input, expected, answer))

test_input = '''0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5
'''
test1(test_input, 6)


# Tests for part 2
def test2(input, expected):
    answer = part2(input)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(input, expected, answer))

test2(test_input, 2)


# The real thing
with open('input12.txt') as fd:
    real_input = fd.read()
answer = part2(real_input)
print('The answer is: {}'.format(answer))
