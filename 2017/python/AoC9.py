class Group():
    def __init__(self, parent):
        self.score = 1 if parent == None else parent.score + 1
        self.parent = parent
        self.subgroups = []
        if parent != None:
            parent.subgroups.append(self)


def part1(input):
    group, _ = buildGroups(input)
    return calculateScore(group)

def part2(input):
    _, total = buildGroups(input)
    return total


S_START = 0
S_GROUP = 1
S_GARBAGE = 2
S_GARBAGE_IGNORE = 3

def buildGroups(input):
    total = 0
    g = None
    s = S_START

    for c in input:
        if s == S_START:
            if c != '{':
                raise ValueError('Doesnt start with {')
            g = Group(None)
            s = S_GROUP

        elif s == S_GROUP:
            if c == '{':
                g = Group(g)
            elif c == '}':
                if g.parent == None:
                    return (g, total)
                g = g.parent
            elif c == '<':
                s = S_GARBAGE

        elif s == S_GARBAGE:
            if c == '!':
                s = S_GARBAGE_IGNORE
            elif c == '>':
                s = S_GROUP
            else:
                total += 1

        elif s == S_GARBAGE_IGNORE:
            s = S_GARBAGE

    return (g, total)


def calculateScore(group):
    return group.score + sum([calculateScore(g) for g in group.subgroups])


# Tests for part 1
def test1(input, expected):
    answer = part1(input)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(input, expected, answer))

test1('{}', 1)
test1('{{{}}}', 6)
test1('{{},{}}', 5)
test1('{{{},{},{{}}}}', 16)
test1('{<a>,<a>,<a>,<a>}', 1)
test1('{{<ab>},{<ab>},{<ab>},{<ab>}}', 9)
test1('{{<!!>},{<!!>},{<!!>},{<!!>}}', 9)
test1('{{<a!>},{<a!>},{<a!>},{<ab>}}', 3)


# Tests for part 2
def test2(input, expected):
    answer = part2(input)
    if answer != expected:
        print('ASSERTION ERROR: {} does not yield {} but {}'.format(input, expected, answer))

test2('{<>}', 0)
test2('{<random characters>}', 17)
test2('{<<<<>}', 3)
test2('{<{!>}>}', 2)
test2('{<!!>}', 0)
test2('{<!!!>>}', 0)
test2('{<{o"i!a,<{i<a>}', 10)


# The real thing
with open('input9.txt', 'r') as content_file:
    content = content_file.read()
answer = part2(content)
print('The answer is: {}'.format(answer))
