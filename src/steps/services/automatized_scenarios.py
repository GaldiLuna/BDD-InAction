from behave import given, when, then

@given('I am a {status} Frequent Flyer member')
def step_impl(context, status):
    assert True

@given('the flying distance between {departure} and {destination} is {distance} km')
def step_impl(context, departure, destination, distance):
    assert True

@given('a Frequent Flyer member {name} has logged in')
def step_impl(context,name):
    context.execute_steps(u"""
        Given {name} is a Frequent Flyer member # Reutiliza os passos anteriores.
        And {name} logs in
    """)

@given('the following accounts')
def step_impl(context): # Dados tabulares são passados na variável context.table.
    for row in context.table:
        owner = row["owner"] # Extrai os valores de cada linha.
        points = row["points"]
        statusPoints = row["statusPoints"]