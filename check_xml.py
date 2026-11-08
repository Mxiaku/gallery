import xml.etree.ElementTree as ET

for lang in ['values', 'values-es']:
    path = f'Android/src/app/src/main/res/{lang}/strings.xml'
    try:
        ET.parse(path)
        print(f'{path}: VALID')
    except ET.ParseError as e:
        print(f'{path}: INVALID - {e}')
