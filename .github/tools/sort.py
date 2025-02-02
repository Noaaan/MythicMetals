import json
from pathlib import Path

ROOT_DIR = Path(__file__).resolve().parent.parent.parent
JSON_DIR = ROOT_DIR / "src/main/resources/assets/mythicmetals/lang"
INPUT = JSON_DIR / "zh_cn.json"  # Original json file to be sorted
OUTPUT = JSON_DIR / "zh_cn.json"  # Output json file, set to the same as INPUT for in-place sorting


def sort_json_keys(obj):
    """
    Sort all dictionary keys in the JSON structure recursively.

    Parameters
    ----------
    obj : dict or list
        JSON data to be sorted (dictionary or list).

    Returns
    -------
        JSON data after reordering.
    """
    if isinstance(obj, dict):
        return {key: sort_json_keys(obj[key]) for key in sorted(obj.keys())}
    elif isinstance(obj, list):
        return [sort_json_keys(item) for item in obj]
    else:
        return obj


def reorder_json_file(input_file, output_file):
    with open(input_file, "r", encoding="utf-8") as f:
        data = json.load(f)

    sorted_data = sort_json_keys(data)

    with open(output_file, "w", encoding="utf-8") as f:
        json.dump(sorted_data, f, ensure_ascii=False, indent=4)


reorder_json_file(INPUT, OUTPUT)

print("JSON sorting completed.")
