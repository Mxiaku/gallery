import re

# Read source strings.xml
src = open('Android/src/app/src/main/res/values/strings.xml', encoding='utf-8').read()

# Read missing string names from PENDIENTES_TRADUCCION.md (section a)
content = open('PENDIENTES_TRADUCCION.md', encoding='utf-8').read()
section_a = content.split('## (a)')[1].split('Total:')[0]
missing_names = []
for line in section_a.strip().split('\n'):
    line = line.strip()
    if line.startswith('- '):
        missing_names.append(line[2:])

# Extract English text for each missing name
entries = {}
pattern = re.compile(r'<string name="([^"]+)"[^>]*>(.*?)</string>', re.DOTALL)
for m in pattern.finditer(src):
    name = m.group(1)
    val = m.group(2).strip()
    if name in missing_names:
        entries[name] = val

# Print results
print(f"Found {len(entries)} of {len(missing_names)} missing names")
for name in sorted(entries.keys()):
    print(f"{name}={entries[name]}")
